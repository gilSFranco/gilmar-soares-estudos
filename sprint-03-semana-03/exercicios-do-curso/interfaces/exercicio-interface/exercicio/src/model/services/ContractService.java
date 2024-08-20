package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;

public class ContractService {
    private OnlinePaymentService paymentService;

    public ContractService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processContract(Contract contract, int months) {
        double basicQuota = contract.getTotalValue() / months;

        for(int i = 0; i < months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i + 1);

            double interest = paymentService.interest(basicQuota, i + 1);
            double fee = paymentService.paymentFee(interest);

            contract.getInstallment().add(new Installment(dueDate, fee));
        }
    }
}
