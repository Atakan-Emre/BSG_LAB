import os
import pandas as pd

# Path where WiFi configurations are stored in Kali Linux
wifi_config_path = '/etc/NetworkManager/system-connections/'

# Extracting the names of the WiFi networks
wifi_networks = os.listdir(wifi_config_path)

wifi_data = []

# Reading each WiFi configuration file
for network in wifi_networks:
    try:
        with open(wifi_config_path + network, 'r') as file:
            lines = file.readlines()
            wifi_name = network
            wifi_password = None
            for line in lines:
                # Extracting the password
                if 'psk=' in line:
                    wifi_password = line.split('=')[1].strip()
                    break
            wifi_data.append({'WiFi Name': wifi_name, 'Password': wifi_password})
    except Exception as e:
        print(f"Error reading {network}: {e}")

# Creating a DataFrame
df = pd.DataFrame(wifi_data)

# Saving to an Excel file
df.to_excel('WiFi_Passwords.xlsx', index=False)
print("WiFi passwords saved to WiFi_Passwords.xlsx")
