
package java.text;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class NumberFormat implements Serializable
{
    private com.google.gwt.i18n.client.NumberFormat format;

    protected NumberFormat()
    {

    }

    protected NumberFormat( com.google.gwt.i18n.client.NumberFormat format )
    {
        applyFormat( format );
    }

    protected void applyFormat( com.google.gwt.i18n.client.NumberFormat format )
    {
        this.format = format;
    }

    public final static NumberFormat getCurrencyInstance()
    {
        return new NumberFormat( com.google.gwt.i18n.client.NumberFormat.getCurrencyFormat() );
    }

    public final static NumberFormat getInstance()
    {
        return new NumberFormat( com.google.gwt.i18n.client.NumberFormat.getDecimalFormat() );
    }

    public final static NumberFormat getIntegerInstance()
    {
        return new NumberFormat( com.google.gwt.i18n.client.NumberFormat.getDecimalFormat() );
    }

    public final static NumberFormat getNumberInstance()
    {
        return new NumberFormat( com.google.gwt.i18n.client.NumberFormat.getDecimalFormat() );
    }

    public final static NumberFormat getPercentInstance()
    {
        return new NumberFormat( com.google.gwt.i18n.client.NumberFormat.getPercentFormat() );
    }

    public final String format( double number )
    {
        return format.format( number );
    }

    public Number parse(String source) throws ParseException
    {
        try
        {
            return format.parse( source );
        }
        catch ( NumberFormatException e )
        {
            throw new ParseException( e.getMessage(), 0 );
        }
    }
}
