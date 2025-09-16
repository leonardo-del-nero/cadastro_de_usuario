package br.com.xerox.teste.domain;

import org.hibernate.annotations.TenantId;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Menu")
@Table(name = "MENUS", uniqueConstraints = @UniqueConstraint(name = "UK_MNU_001",columnNames = {"LABEL","TENANT"}))
public class MenuEntity {
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String label;

    @Column(nullable = false, length = 4000)
    private String url;

    @TenantId
    @Column(nullable = false, length = 255)
    private String tenant;
}
