package persistence

import javax.persistence.*

@Entity
@Table(name = "SETTINGS")
class SettingsEntity {
    @Id
    @GeneratedValue
    @Column
    var id: Long = 0

    @Column(nullable = false, unique = true)
    var key: String? = null

    @Column(nullable = false)
    var value: String? = null

    companion object {
        val defaultPassword = "057ba03d6c44104863dc7361fe4578965d1887360f90a0895882e58a6248fc86"        // 'changeme'
        val appPasswordKey = "APP_PASSWORD"
    }
}