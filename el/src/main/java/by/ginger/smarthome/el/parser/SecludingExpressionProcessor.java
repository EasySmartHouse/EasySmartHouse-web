/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.el.parser;

/**
 *
 * @author mirash
 */
public class SecludingExpressionProcessor implements ExpressionProcessor {

    private static final String DELIM = ExpressionEntities.DELIM;
    private String[] toSeclude = {"(", ")", ">", "<", "=="};

    private void seclude(StringBuilder exprBuilder, String occur) {
        int index = exprBuilder.indexOf(occur);
        int offset = 0;

        while (index >= 0) {
            offset = index;

            if (offset - 1 > 0) {
                exprBuilder = exprBuilder.insert(index, DELIM);
            }
            exprBuilder.insert(index + occur.length()+1, DELIM);

            index = exprBuilder.indexOf(occur, index + 2*DELIM.length() + 1);
        }

    }

    @Override
    public String preProcess(String expression) {
        StringBuilder exprBuilder = new StringBuilder(expression);

        for (int i = 0; i < toSeclude.length; i++) {
            String token = toSeclude[i];
            seclude(exprBuilder, token);
        }

        return exprBuilder.toString();
    }

}
