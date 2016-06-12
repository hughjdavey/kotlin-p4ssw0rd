package persistence

class PasswordService {
    init {
        // FOR DEVELOPMENT PURPOSES
        //initTestData()
    }

    private fun getSettings(): List<SettingsEntity> {
        val settings: List<SettingsEntity>
        val session = PasswordStorage.openSession()
        session.beginTransaction()
        settings = session.createQuery("from SettingsEntity").list() as List<SettingsEntity>
        session.transaction.commit()
        session.close()
        return settings
    }

    private fun getSetting(key: String): SettingsEntity? {
        return getSettings().find { it.key == key }
    }

    private fun getSettingValue(key: String): String? {
        return getSettings().find { it.key == key }?.value
    }

    private fun saveSetting(setting: SettingsEntity) {
        val session = PasswordStorage.openSession()
        session.beginTransaction()
        session.saveOrUpdate(setting)
        session.transaction.commit()
        session.close()
    }

    fun getAppPassword(): String {
        return getSettingValue(SettingsEntity.appPasswordKey) ?: SettingsEntity.defaultPassword
    }

    fun setAppPassword(appPassword: String) {
        val passwordSetting = getSetting(SettingsEntity.appPasswordKey) ?: SettingsEntity()
        passwordSetting.key = SettingsEntity.appPasswordKey
        passwordSetting.value = appPassword
        saveSetting(passwordSetting)
    }


    fun addPassword(password: PasswordEntity) {
        val session = PasswordStorage.openSession();
        session.beginTransaction()
        session.save(password);
        session.transaction.commit();
        session.close();
    }

    fun listPasswords(): List<PasswordEntity> {
        val passwords: List<PasswordEntity>
        val session = PasswordStorage.openSession();
        session.beginTransaction()
        passwords = session.createQuery("from PasswordEntity").list() as List<PasswordEntity>;
        session.transaction.commit();
        session.close();
        return passwords
    }

    fun listPasswords(search: String): List<PasswordEntity> {
        val passwords: List<PasswordEntity>
        val session = PasswordStorage.openSession();
        session.beginTransaction()
        passwords = session.createQuery(
                "from PasswordEntity p where p.name like \'%$search%\' or p.username like \'%$search%\' or p.email like \'%$search%\' or p.url like \'%$search%\'")
                .list() as List<PasswordEntity>;
        session.transaction.commit();
        session.close();
        passwords.forEach { println(it) }
        return passwords
    }

    fun removePassword(passwordId: Long) {
        val session = PasswordStorage.openSession();
        session.beginTransaction()
        val entity = session.load(PasswordEntity::class.java, passwordId)
        session.delete(entity);
        session.transaction.commit();
        session.close();
    }

    fun updatePassword(password: PasswordEntity) {
        val session = PasswordStorage.openSession();
        session.beginTransaction()
        session.update(password);
        session.transaction.commit();
        session.close();
    }

    fun initTestData() {
        samplePasswordList().forEach { addPassword(it) }
    }

    private fun samplePasswordList(): List<PasswordEntity> {
        val p1 = PasswordEntity().testPassword("Gmail")
        val p2 = PasswordEntity().testPassword("Hotmail")
        val p3 = PasswordEntity().testPassword("Lloyds")
        val p4 = PasswordEntity().testPassword("Santander")
        val p5 = PasswordEntity().testPassword("Snapchat")
        val p6 = PasswordEntity().testPassword("Facebook")
        val p7 = PasswordEntity().testPassword("Deliveroo")
        val p8 = PasswordEntity().testPassword("Uber")
        val p9 = PasswordEntity().testPassword("BT")
        val p10 = PasswordEntity().testPassword("Virgin Media")
        return listOf(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
    }
}