
package chatserver;

import java.util.Date;
import java.util.StringTokenizer;
import java.util.function.Supplier;


public class Util {
    public static TokenizerFactory<StringTokenizer> tokenize = StringTokenizer::new;
    public static Supplier<Date> newDateSupplier = Date::new;
}
