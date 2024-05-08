package com.brunch.api.entity;

import com.brunch.api.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;

    public boolean isActive() {
        return isActive;
    }

    private boolean isActive;
//    @Lob
//    @Column(name = "imagedata", length = 100000)
//    private byte[] imageData;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @NotNull
    @JoinColumn(name = "id_event", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("imageData")
    private Event event__;
}
