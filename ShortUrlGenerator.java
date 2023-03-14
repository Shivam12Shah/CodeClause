import java.util.HashMap;
import java.util.Random;

public class ShortUrlGenerator {
    // Define the base URL for the short URLs
    private static final String BASE_URL = "http://google.com";

    // Define the set of characters used to generate the short code
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // Define the length of the short code
    private static final int CODE_LENGTH = 6;

    // Define two hash maps to store the mappings between the long URLs and the short codes
    private static HashMap<String, String> urlToCode = new HashMap<>();
    private static HashMap<String, String> codeToUrl = new HashMap<>();

    public static void main(String[] args) {
        // Test the program by generating a short URL for a long URL
        String longUrl = "https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwiqiebhjNz9AhVDc3AKHSllCSsQPAgI";
        String shortUrl = getShortUrl(longUrl);
        System.out.println("Short URL for " + longUrl + " is " + shortUrl);

        // Test the program by retrieving the original long URL for a short URL
        String originalUrl = getOriginalUrl(shortUrl);
        System.out.println("Original URL for " + shortUrl + " is " + originalUrl);
    }

    /**
     * Generates a short URL for a given long URL.
     * @param longUrl the long URL to generate a short URL for
     * @return the short URL for the given long URL
     */
    public static String getShortUrl(String longUrl) {
        // Check if the long URL has already been assigned a short code
        if (urlToCode.containsKey(longUrl)) {
            return BASE_URL + urlToCode.get(longUrl);
        }

        // Generate a unique short code
        String code;
        do {
            code = generateCode();
        } while (codeToUrl.containsKey(code));

        // Store the mapping between the long URL and the short code
        urlToCode.put(longUrl, code);
        codeToUrl.put(code, longUrl);

        // Return the full short URL
        return BASE_URL + code;
    }

    /**
     * Retrieves the original long URL for a given short URL.
     * @param shortUrl the short URL to retrieve the original long URL for
     * @return the original long URL for the given short URL
     */
    public static String getOriginalUrl(String shortUrl) {
        // Extract the short code from the full short URL
        String code = shortUrl.replace(BASE_URL, "");

        // Look up the original long URL in the codeToUrl hash map
        return codeToUrl.getOrDefault(code, "");
    }

    /**
     * Generates a random short code of length CODE_LENGTH using the CHARACTERS string.
     * @return a random short code
     */
    private static String generateCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < CODE_LENGTH; i++) {
            // Generate a random index into the CHARACTERS string
            int index = random.nextInt(CHARACTERS.length());

            // Append the character at the random index to the short code
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }
}
