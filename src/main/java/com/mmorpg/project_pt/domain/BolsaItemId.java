package com.mmorpg.project_pt.domain;

import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BolsaItemId implements Serializable {

    private Long personagem;
    private Long item;
}