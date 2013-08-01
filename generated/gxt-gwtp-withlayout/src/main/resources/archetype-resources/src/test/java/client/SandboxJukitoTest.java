#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client;

import static org.junit.Assert.assertTrue;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JukitoRunner.class)
public class SandboxJukitoTest {
    public static class Module extends JukitoModule {
        @Override
        protected void configureTest() {
        }
    }

    @Test
    public void testSandbox() {
        assertTrue(true);
    }
}
