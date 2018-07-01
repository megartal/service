package diringo.services.services;

import diringo.services.documents.Rate;
import diringo.services.repositories.RateRepository;
import org.springframework.stereotype.Service;

/**
 * @Author Akbar
 * @DATE 5/24/2018.
 */
@Service
public class RateService {
    private final RateRepository rateRepository;

    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public void add(Rate rate) {
        rateRepository.save(rate);
    }
}
