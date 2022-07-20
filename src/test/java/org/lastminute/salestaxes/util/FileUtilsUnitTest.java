package org.lastminute.salestaxes.util;

import org.junit.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUtilsUnitTest {

    @Test
    public void test_getFileAsIOStream_ShouldReturnInputStream() {
        //given
        String fileName = "/test1.txt";

        //when
        InputStream is = FileUtils.getFileAsIOStream(fileName);

        //then
        assertThat(is).isNotNull();
        assertThat(is).isNotEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getFileAsIOStream_ShouldThrowException() {
        //given
        String fileName = "/test.txt";

        //when
        try {
            FileUtils.getFileAsIOStream(fileName);
        } catch (Exception e) {
            //then
            assertThat(e.getMessage()).isEqualTo("/test.txt is not found");

            throw e;
        }

    }
}
