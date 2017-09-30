package at.favre.crypto;

import org.junit.Test;

import javax.crypto.Mac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HkdfMacFactoryTest {
    @Test
    public void hmacSha256() throws Exception {
        testHmacFactory(HkdfMacFactory.Default.hmacSha256(), 32);
    }

    @Test
    public void hmacSha512() throws Exception {
        testHmacFactory(HkdfMacFactory.Default.hmacSha512(), 64);
    }

    @Test
    public void hmacSha1() throws Exception {
        testHmacFactory(new HkdfMacFactory.Default("HmacSha1", 20), 20);
    }

    @Test
    public void hmacMd5() throws Exception {
        testHmacFactory(new HkdfMacFactory.Default("HmacMd5", 16), 16);
    }

    @Test(expected = RuntimeException.class)
    public void hmacInstanceNotExisting() throws Exception {
        new HkdfMacFactory.Default("HmacNotExisting", 16).createMacInstance(new byte[16]);
    }

    private void testHmacFactory(HkdfMacFactory macFactory, int refLength) {
        Mac mac = macFactory.createMacInstance(new byte[refLength]);
        assertNotNull(mac);

        mac.update(new byte[]{0x76, (byte) 0x92, 0x0E, 0x5E, (byte) 0x85, (byte) 0xDB, (byte) 0xA7, (byte) 0x8F});
        byte[] hash = mac.doFinal();
        assertEquals(refLength, hash.length);
        assertEquals(refLength, macFactory.macHashLengthByte());
    }
}