/*
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com.braicu.jcm.layout;



import java.util.*;




/**
 * The following inner class is used to bind components to their
 * constraints.
 *
 * @author  Daniel E. Barbalace
 */

public class TableLayoutConstraints implements TableLayoutConstants
{



/** Cell in which the upper left corner of the component lays */
public int col1, row1;

/** Cell in which the lower right corner of the component lays */
public int col2, row2;

/** Horizontal justification if component occupies just one cell */
public int hAlign;

/** Verical justification if component occupies just one cell */
public int vAlign;



/**
 * Constructs an TableLayoutConstraints with the default settings.  This
 * constructor is equivalent to TableLayoutConstraints(0, 0, 0, 0, FULL, FULL).
 */

public TableLayoutConstraints ()
{
    col1 = row1 = col2 = col2 = 0;
    hAlign = vAlign = FULL;
}



/**
 * Constructs an TableLayoutConstraints from a string.
 *
 * @param constraints    indicates TableLayoutConstraints's position and justification
 *                       as a string in the form "row, column" or
 *                       "row, column, horizontal justification, vertical
 *                       justification" or "row 1, column 1, row 2, column 2".
 *                       It is also acceptable to delimit the paramters with
 *                       spaces instead of commas.
 */

public TableLayoutConstraints (String constraints)
{
    // Parse constraints using spaces or commas
    StringTokenizer st = new StringTokenizer(constraints, ", ");

    // Use default values for any parameter not specified or specified
    // incorrectly.  The default parameters place the component in a single
    // cell at column 0, row 0.  The component is fully justified.
    col1 = 0;
    row1 = 0;
    col2 = 0;
    row2 = 0;
    hAlign = FULL;
    vAlign = FULL;

    String token = null;

    try
    {
        // Get the first column (assume component is in only one column)
        token = st.nextToken();
        col1 = new Integer(token).intValue();
        col2 = col1;

        // Get the first row (assume component is in only one row)
        token = st.nextToken();
        row1 = new Integer(token).intValue();
        row2 = row1;

        // Get the second column
        token = st.nextToken();
        col2 = new Integer(token).intValue();

        // Get the second row
        token = st.nextToken();
        row2 = new Integer(token).intValue();
    }
    catch (NoSuchElementException error) {}
    catch (NumberFormatException error)
    {
        try
        {
            // Check if token means horizontally justification the component
            if (token.equalsIgnoreCase("L"))
                hAlign = LEFT;
            else if (token.equalsIgnoreCase("C"))
                hAlign = CENTER;
            else if (token.equalsIgnoreCase("F"))
                hAlign = FULL;
            else if (token.equalsIgnoreCase("R"))
                hAlign = RIGHT;

            // There can be one more token for the vertical justification even
            // if the horizontal justification is invalid
            token = st.nextToken();

            // Check if token means horizontally justification the component
            if (token.equalsIgnoreCase("T"))
                vAlign = TOP;
            else if (token.equalsIgnoreCase("C"))
                vAlign = CENTER;
            else if (token.equalsIgnoreCase("F"))
                vAlign = FULL;
            else if (token.equalsIgnoreCase("B"))
                vAlign = BOTTOM;
        }
        catch (NoSuchElementException error2) {}
    }

    // Make sure row2 >= row1
    if (row2 < row1)
        row2 = row1;

    // Make sure col2 >= col1
    if (col2 < col1)
        col2 = col1;
}



/**
 * Constructs an TableLayoutConstraints a set of constraints.
 *
 * @param col1      column where upper-left cornor of the component is placed
 * @param row1      row where upper-left cornor of the component is placed
 * @param col2      column where lower-right cornor of the component is placed
 * @param row2      row where lower-right cornor of the component is placed
 * @param hAlign    horizontal justification of a component in a single cell
 * @param vAlign    vertical justification of a component in a single cell
 */

public TableLayoutConstraints
    (int col1, int row1, int col2, int row2, int hAlign, int vAlign)
{
    this.col1 = col1;
    this.row1 = row1;
    this.col2 = col2;
    this.row2 = row2;

    if ((hAlign < MIN_ALIGN) || (hAlign > MAX_ALIGN))
        this.hAlign = FULL;
    else
        this.hAlign = hAlign;

    if ((vAlign < MIN_ALIGN) || (vAlign > MAX_ALIGN))
        this.vAlign = FULL;
    else
        this.vAlign = vAlign;
}



/**
 * Gets a string representation of this TableLayoutConstraints.
 *
 * @return a string in the form "row 1, column 1, row 2, column 2" or
 *         "row, column, horizontal justification, vertical justification"
 */

public String toString ()
{
    StringBuffer buffer = new StringBuffer();

    try{

    buffer.append (row1);
    buffer.append (", ");
    buffer.append (col1);
    buffer.append (", ");

    if ((row1 == row2) && (col1 == col2))
    {
        final char h[] = {'L', 'C', 'F', 'R'};
        final char v[] = {'T', 'C', 'F', 'B'};

        buffer.append (h[hAlign]);
        buffer.append (", ");
        buffer.append (v[vAlign]);
    }
    else
    {
        buffer.append (row2);
        buffer.append (", ");
        buffer.append (col2);
    }
    } catch (Exception ex){
      ex.printStackTrace();
    }
    return buffer.toString();
}



}
