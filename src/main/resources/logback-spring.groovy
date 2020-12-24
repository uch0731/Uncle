import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.DEBUG

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        Pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} %highlight(%5level) --- %cyan(%-30.30logger{29}) : %msg%n"
    }
}

root(INFO, ["CONSOLE"])
logger("org.uch", DEBUG, ["CONSOLE"], false)

