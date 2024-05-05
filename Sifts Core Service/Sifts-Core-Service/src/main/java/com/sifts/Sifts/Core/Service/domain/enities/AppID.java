package com.sifts.Sifts.Core.Service.domain.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author Temple Jack chiorlu
 */
@Entity
@Table(name = "APPID_TB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppID extends BaseEntity {
    private String appName;
    private String appPassword;
    private boolean channelStatus;

}
