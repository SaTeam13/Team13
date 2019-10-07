package com.sut.sa.g13.Entity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="PACKAGETYPE")
public class PackageType {

    @Id
    @SequenceGenerator(name="packagetype_seq",sequenceName="packagetype_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="packagetype_seq")  
    @Column(name = "PACKAGETYPE_ID", unique = true, nullable = true)
    private @NonNull Long packagetypeid;
    private @NonNull String time;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<PaymentMember> pay;

}
