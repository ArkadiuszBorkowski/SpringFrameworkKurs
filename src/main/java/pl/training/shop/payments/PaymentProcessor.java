package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.javamoney.moneta.Money;
import pl.training.shop.time.TimeProvider;

@Log
@RequiredArgsConstructor
public class PaymentProcessor implements PaymentService {


    private static final PaymentStatus DEFAULT_PAYMENT_STATUS = PaymentStatus.STARTED;

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentRepository paymentsRepository;
    private final TimeProvider timeProvider;

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var paymentValue = calculatePaymentValue(paymentRequest.getValue());
        var payment = createPayment(paymentValue);

        return paymentsRepository.save(payment);
    }


    private Payment createPayment(Money paymentValue) {
        return Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentValue)
                .timestamp(timeProvider.getTimeStamp())
                .status(DEFAULT_PAYMENT_STATUS)
                .build();
    }

    private Money calculatePaymentValue(Money paymentValue) {
        var paymentFee = paymentFeeCalculator.calculateFee(paymentValue);
        return paymentValue.add(paymentFee);
    }

}
