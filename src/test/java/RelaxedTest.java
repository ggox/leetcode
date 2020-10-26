import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * @Author: ggox @Date: 2020/7/12 01:02
 */
public class RelaxedTest {

	public String whereClass(Class<?> clazz) {
		ProtectionDomain protectionDomain = clazz.getProtectionDomain();
		CodeSource codeSource = protectionDomain.getCodeSource();
		URI location = null;
		try {
			location = (codeSource == null ? null : codeSource.getLocation().toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException("uri获取异常");
		}
		String path = (location == null ? null : location.getSchemeSpecificPart());
		if (path == null) {
			throw new IllegalStateException("Unable to determine code source archive");
		}
		return path;
	}

	public String where(final Class cls) {
		if (cls == null)
			throw new IllegalArgumentException("null input: cls");
		URL result = null;
		final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
		final ProtectionDomain pd = cls.getProtectionDomain();
		if (pd != null) {
			final CodeSource cs = pd.getCodeSource();
			if (cs != null)
				result = cs.getLocation();
			if (result != null) {
				if ("file".equals(result.getProtocol())) {
					try {
						if (result.toExternalForm().endsWith(".jar") || result.toExternalForm().endsWith(".zip"))
							result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
						else if (new File(result.getFile()).isDirectory())
							result = new URL(result, clsAsResource);
					} catch (MalformedURLException ignore) {
					}
				}
			}
		}
		if (result == null) {
			final ClassLoader clsLoader = cls.getClassLoader();
			result = clsLoader != null ? clsLoader.getResource(clsAsResource) : ClassLoader.getSystemResource(clsAsResource);
		}
		return result == null ? null : result.toString();
	}

	private void test() {
		for (int a = 0; a < 10; a++) {
			System.out.println(a + 1);
		}
	}

	public static void main(String[] args) {
		Integer a = new Integer("135");
		Integer b = new Integer("456");
		System.out.println(a + b);
	}
}
