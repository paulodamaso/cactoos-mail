package com.github.fabriciofx.cactoos.mail.header;

import com.github.fabriciofx.cactoos.mail.Header;
import java.io.OutputStream;
import org.cactoos.Output;
import org.cactoos.io.BytesOf;
import org.cactoos.text.FormattedText;

public abstract class HeaderEnvelope implements Header {
    private final Output origin;
    private final String name;
    private final String value;

    public HeaderEnvelope(
        final Output output,
        final String name,
        final String value
    ) {
        this.origin = output;
        this.name = name;
        this.value = value;
    }

    @Override
    public OutputStream stream() throws Exception {
        final OutputStream stream = this.origin.stream();
        stream.write(
            new BytesOf(
                new FormattedText("%s: %s\r\n", this.name, this.value)
            ).asBytes()
        );
        return stream;
    }
}
