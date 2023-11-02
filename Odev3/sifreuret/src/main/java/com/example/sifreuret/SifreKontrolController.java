package com.example.sifreuret;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  // Bu sınıfın bir Spring Controller olduğunu belirtir.
@EnableScheduling  // Zamanlanmış görevleri (yani @Scheduled anotasyonunu) etkinleştirir.
public class SifreKontrolController {

    // Başlangıçta rastgele bir şifre oluştur.
    private String aktifSifre = SifreUretici.rastgeleSifreUret();
    // Şifrenin ne zaman yenileneceğini belirten zaman damgası.
    private long sifreYenilemeZamani = System.currentTimeMillis() + 120000;
    // Sayaçın çalışıp çalışmadığını kontrol eden bayrak.
    private boolean sayacCalisiyor = true;

    // Ana sayfaya (index) erişim için bir HTTP GET yönlendirmesi.
    @GetMapping("/")
    public String anaSayfa(Model model) {
        model.addAttribute("uretilenSifre", aktifSifre);
        model.addAttribute("sifreYenilemeZamani", sifreYenilemeZamani);
        return "giris";  // giris.html şablonuna yönlendir.
    }

    // Şifre kontrolü için bir HTTP POST yönlendirmesi.
    @PostMapping("/kontrol")
    @ResponseBody
    public String sifreKontrol(@RequestParam String sifre) {
        if (sifre.equals(aktifSifre)) {
            sayacCalisiyor = false;
            System.out.println("Giriş yapıldı!");  // Doğru giriş yapıldığında terminalde log kaydı.
            return "Giriş yaptınız!";
        } else {
            System.out.println("Tekrar deneyiniz.");  // Hatalı giriş yapıldığında terminalde log kaydı.
            return "Tekrar deneyiniz.";
        }
    }

    // Şifreyi yeniden üretmek için bir HTTP GET yönlendirmesi.
    @GetMapping("/tekrar-dene")
    public String tekrarDene() {
        aktifSifre = SifreUretici.rastgeleSifreUret();  // Şifreyi yeniden üret.
        sifreYenilemeZamani = System.currentTimeMillis() + 120000;  // Zaman damgasını güncelle.
        sayacCalisiyor = true;
        System.out.println("Yeni şifre (tekrar dene ile): " + aktifSifre);  // Şifreyi terminalde göster.
        return "redirect:/";  // Ana sayfaya yönlendir.
    }

    // Belirtilen aralıklarla (bu örnekte 2 dakika) çalışacak zamanlanmış görev.
    @Scheduled(fixedRate = 120000)
    public void sifreYenile() {
        if (!sayacCalisiyor) {
            return;
        }
        aktifSifre = SifreUretici.rastgeleSifreUret();
        sifreYenilemeZamani = System.currentTimeMillis() + 120000;
        System.out.println("Yeni şifre: " + aktifSifre);  // Şifreyi terminalde göster.
    }
}
