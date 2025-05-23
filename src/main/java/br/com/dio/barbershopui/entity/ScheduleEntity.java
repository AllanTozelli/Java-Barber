package br.com.dio.barbershopui.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(
        name="SCHEDULES",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_EMAIL", columnNames = {"start_at", "end_at"}),
        }
)

@Getter
@Setter
@ToString
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, name = "Start_at")
    private OffsetDateTime startAt;

    @Column(nullable = false, name = "End_at")
    private OffsetDateTime endAt;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client = new ClientEntity();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleEntity that = (ScheduleEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(startAt, that.startAt) && Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startAt, endAt);
    }
}
