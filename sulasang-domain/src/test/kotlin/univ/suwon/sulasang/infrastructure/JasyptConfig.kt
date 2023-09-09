package univ.suwon.sulasang.infrastructure

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.test.context.junit.jupiter.SpringExtension
import univ.suwon.sulasang.common.common.config.JasyptConfig

@Disabled
@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [JasyptConfig::class])
class EncryptionTest {

    @Value("\${jasypt.encryptor.password}")
    lateinit var jasyptEncryptorPassword: String

    @Autowired
    lateinit var configurableEnvironment: ConfigurableEnvironment

    lateinit var encryptor: DefaultLazyEncryptor

    @BeforeEach
    internal fun setUp() {
        check(jasyptEncryptorPassword.isNotBlank()) {
            "jasypt.encryptor.password must not be null, empty or blank. "
        }
        encryptor = DefaultLazyEncryptor(configurableEnvironment)
    }

    @Test
    fun testForEncryption() {
        val encryptTarget = "test"
        println("source: $encryptTarget")
        println("encrypted: ${encryptor.encrypt(encryptTarget)}")
    }

    @Test
    fun testForDecryption() {
        val decryptTarget = "test"
        println("source: $decryptTarget")
        println("decrypted: ${encryptor.decrypt(decryptTarget)}")
    }
}
