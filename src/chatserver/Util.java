/*
Copyright (c) 2010-2016 Ekaterina A. Ivanova (iceja.net). All rights reserved. 
PROPRIETARY. For demo purposes only, not for redistribution or any commercial 
use.
*/

package chatserver;

import java.util.Date;
import java.util.StringTokenizer;
import java.util.function.Supplier;

/**
 * date Jul 15, 2010
 * @author Ekaterina A. Ivanova
 */

public class Util {
    public static TokenizerFactory<StringTokenizer> tokenize = StringTokenizer::new;
    public static Supplier<Date> newDateSupplier = Date::new;
}
