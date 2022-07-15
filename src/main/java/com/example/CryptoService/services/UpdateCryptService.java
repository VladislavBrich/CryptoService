package com.example.CryptoService.services;

import com.example.CryptoService.dto.TickerDto;
import com.example.CryptoService.models.Crypt;
import com.example.CryptoService.models.Notify;
import com.example.CryptoService.repositories.CryptRepository;
import com.example.CryptoService.repositories.NotifyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UpdateCryptService {

    @Autowired
    private CryptRepository cryptRepository;

    @Autowired
    private NotifyRepository notifyRepository;

    @Scheduled(fixedRate = 60000)
    public void updateRate() {
        List<Crypt> crypts = cryptRepository.findAll();
        for (Crypt c : crypts) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.coinlore.net/api/ticker/?id=" + c.getCryptId();
            try {
                TickerDto[] result = restTemplate.getForObject(url, TickerDto[].class);
                c.setPrice(Double.parseDouble(result[0].getPrice_usd()));
                cryptRepository.save(c);
            } catch (Exception e) {
                log.error(url);
            }
            sendNotify(c);
        }
    }

    private void sendNotify(Crypt c) {
        log.info(c.getCryptoName() + " [" + c.getPrice() + "]");
        Double pp = c.getPrice();
        List<Notify> notifies = new ArrayList<>();
        notifies.addAll(notifyRepository.getNotifyListPP(c, pp));
        notifies.addAll(notifyRepository.getNotifyListMM(c, pp));
        for (Notify n : notifies) {
            StringBuffer sb = new StringBuffer();
            sb.append(c.getCryptoName());
            sb.append(" ");
            sb.append(n.getUserNotify().getUsername());
            sb.append(" [");
            sb.append(pp);
            sb.append(" : ");
            sb.append(n.getPrice());
            sb.append("] ");
            Double p = pp - n.getPrice();
            Double pr = 100 * p / pp;
            sb.append(pr);
            log.warn(sb.toString());
        }
    }

}
