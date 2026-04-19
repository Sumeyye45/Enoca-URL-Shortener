package entites;

import jakarta.persistence.*;



import java.time.LocalDateTime;

@Entity
public class Url {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String originalUrl;

        @Column(unique = true)
        private String shortCode;

        private long clickCount;

        private LocalDateTime createdAt;

        public Url(Long id, String originalUrl, String shortCode, long clickCount, LocalDateTime createdAt) {
                this.id = id;
                this.originalUrl = originalUrl;
                this.shortCode = shortCode;
                this.clickCount = clickCount;
                this.createdAt = createdAt;
        }

        public Url() {

        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getOriginalUrl() {
                return originalUrl;
        }

        public void setOriginalUrl(String originalUrl) {
                this.originalUrl = originalUrl;
        }

        public String getShortCode() {
                return shortCode;
        }

        public void setShortCode(int shortCode) {
                this.shortCode = shortCode;
        }

        public long getClickCount() {
                return clickCount;
        }

        public void setClickCount(long clickCount) {
                this.clickCount = clickCount;
        }

        public LocalDateTime getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
                this.createdAt = createdAt;
        }


    }


















