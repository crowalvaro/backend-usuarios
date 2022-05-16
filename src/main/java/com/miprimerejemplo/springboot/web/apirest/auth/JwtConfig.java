package com.miprimerejemplo.springboot.web.apirest.auth;

public class JwtConfig {
	public static final String MI_LLAVE = "llaveprivada";
	public static final String RSA_PRIVATE = "\r\n"
			+ "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEowIBAAKCAQEAoq5aw5HzZRIz0dKTPqKn9ygejvNg5sHPGnCbjA++aHms3Z0n\r\n"
			+ "fpHNGBiQ2KDNE5O+KjMKGJwboePEwurFDw1fKK7M38M1nnQ2J+E66+cVdyuH9dkq\r\n"
			+ "FHQguZOG/QhJAHMv37I58tQVjHbRw6fjhTu7FCqYgpCLr5dKUj7IslNHw1QukHDq\r\n"
			+ "/KRWP4VktOqkaA1fzycO9UkKmnZfzqSTMOcLQF96O0uiOUrlHpU390cQOoc2IEkZ\r\n"
			+ "kVXatlbKJ53m299aQos94e8+Vd8zX+YTiTSSL3XwoZYVQephWwDMSXBgMtSsEdR2\r\n"
			+ "+2UpAGQQXcgPDbjIuAZEHV6clOUuJI91rvX81wIDAQABAoIBAHVK6L4hOTAiU6Jx\r\n"
			+ "BSEf/aLCFmZTLAa0rjIOBtiRcWO6dtzCNnuXfBxneef26GW5HF8otZrQ00uCAZfX\r\n"
			+ "1YToyzd18NlU0hAREgrm5ai40E0/4aNslF5y7DVhHO7LTnc4lcffpNBkAbVo5gz/\r\n"
			+ "u5eQXW5I1/UrasuLdVafVezdBtzZapTu+/1Q9+ohuVg9lM5B1sUQhb3HymEtYz4q\r\n"
			+ "MTaxnKEt5MzlTPLmWfjJ4KeuXiDDn9/U9q8s8CxerM9Rp8vOOU1YYrZpbd10oRkO\r\n"
			+ "cjZSw5K4L5XU3tr6fz0/Y6hOh047FdC1hN6GVgWvxMPgG1PwhDQ9dGIQwNIDxVTm\r\n"
			+ "2AQzj+ECgYEA11qw8ZW9rYS1MaUVmjBoCPISRLmCW86oQyB38ofH7vffnOCXhXRG\r\n"
			+ "rpUIMkL9B4/KRBkBhHActqDs7aZ9bXHkoD0iHteYocsJY7ywstLV6775ZPa+9wQU\r\n"
			+ "+U0Qy8T3yo8IiDGhUNiKPL72FN6dJbIj32DCPNfJWJw01Y3Le1Q/g20CgYEAwWKk\r\n"
			+ "0L7WP1ktJj5doHasLoor8OzttiMpj8GKW7LWJdMzvsh0vHvsgAzVuIZ10jvnIq7Q\r\n"
			+ "W0Rw0T/1w2B6FrzhwW9Z6GoJcH27fRVaM2pt2lrs1Rl9e6UAip8CyTFk4z1d/Gn3\r\n"
			+ "WZrJeYdq2TcQN5cGtJFbV9Ne2wZ36w9SEep7EtMCgYAopdOUJeFhaRUSQ8iqC8z3\r\n"
			+ "M0nDcwhp773OvcgmHP7d2id44uN2qxXUSgj8A6ziyn8xD088AjVJO2K9EJnIGKFA\r\n"
			+ "pYlVLPiAQWSKUMlBV45NKwTDy9cLpNeYnrU24PysXQcNPeDm85hUEu87zTD4NoGS\r\n"
			+ "3h6LgWddtE48qMDpjGNYmQKBgBgAbEBUTzcRlF74wpoV4IzuLUH7xg0jFBd5h/iV\r\n"
			+ "m6qaKgP10TdLkjDaZvQ+DAWJ1cgiNk39kpsF+zTClqSX0NU3QstrzbweftSpf8Z9\r\n"
			+ "4SCGxHo2bbKY6hZKYggij0j85ttbM431figNPJ+1C9g6cGSc3d9qpo08okmUXzVG\r\n"
			+ "CvxtAoGBAMH7Gaw5yhtu22jO9Ng8jBYjsR4LQddVKMOqcvcAspHSNP8XqZas41RW\r\n"
			+ "0tYFCm2cOaN6ISb7ZZXgQiSu3p09iYmXaNeZ6v8W8+X/PSNGij+RdxbriiM0NHmz\r\n"
			+ "bv5tOSAUhgxq26JFrg+RgxA0PEk3maBNFSgyhMzxEiaRqOBp0i5t\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoq5aw5HzZRIz0dKTPqKn\r\n"
			+ "9ygejvNg5sHPGnCbjA++aHms3Z0nfpHNGBiQ2KDNE5O+KjMKGJwboePEwurFDw1f\r\n"
			+ "KK7M38M1nnQ2J+E66+cVdyuH9dkqFHQguZOG/QhJAHMv37I58tQVjHbRw6fjhTu7\r\n"
			+ "FCqYgpCLr5dKUj7IslNHw1QukHDq/KRWP4VktOqkaA1fzycO9UkKmnZfzqSTMOcL\r\n"
			+ "QF96O0uiOUrlHpU390cQOoc2IEkZkVXatlbKJ53m299aQos94e8+Vd8zX+YTiTSS\r\n"
			+ "L3XwoZYVQephWwDMSXBgMtSsEdR2+2UpAGQQXcgPDbjIuAZEHV6clOUuJI91rvX8\r\n"
			+ "1wIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
	
}
