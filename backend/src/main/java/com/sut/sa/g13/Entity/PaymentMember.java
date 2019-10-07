package com.sut.sa.g13.Entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
@Data
@Entity
@NoArgsConstructor
@Table(name="Payment")
public class PaymentMember {
    @Id
    @SequenceGenerator(name="payment_seq",sequenceName="reservation_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="reservation_seq")
    @Column(name = "PAYMENT_ID", unique = true, nullable = true)
    private @NonNull Long paymentid;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", insertable = true)
    private Customer customerid; //edit
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Category.class)
    @JoinColumn(name = "CUSTOMER_TYPE", insertable = true)
    private Category categoryid; //edit
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employeeid; //edit
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PackageType.class)
    @JoinColumn(name = "PACKAGE_TYPE", insertable = true)
    private PackageType packagetypeid;
}

