package controller;
public class Parser {
    // This function accept expression string and calculate result throw calling other methods
    public String calculate( String expression ) throws Exception{
        expression = openBrackets( expression ); 
        expression = calcMultDivSequences( expression );
        String result = calcSequence( expression ); // here expression should contain only numbers, '+', and '~'
        //operations so we can just calc it as sequence of operation with equal priority
        return result;
    }
 
    // Example: if expression is "-1 - 1 * ( 3 - 8 ) - 1" it should return  "-1 ~ 1 * ( 3 ~ 8 ) ~ 1"
    // As you can this explicitly separate  "-" as atribute of negative value and "-" as ariphmetical operation
    // Important !1! this function should be called only once as very first method
    public String negativeValueFix( String expression ){ // fix, bloody fix...
        expression.replace( '~', '!' );
        int index = 0;
        char currentChar;
        char prevChar = '(';
        while( index < expression.length() ){
            currentChar = expression.charAt( index );
            if( currentChar == '-' && prevChar != '(' ){
                expression = expression.substring( 0, index ) +
                        "~" +
                        expression.substring( index + 1 );
            }else if( currentChar != ' ' ){
                prevChar = currentChar;
            }
            index++;
        }
        return expression;
    }
 
    // Example: ( 1 + 1 ) * 3 ~ ( 4 / 2 ) should return 2 * 3 ~ 2
    private String openBrackets( String expression )throws Exception{
        int index = expression.length() - 1;
        int lastRightBracketIndex = 0;
        int openedBracketsAmnt = 0;
        char currentChar;
        while( -1 < index  ){
            currentChar = expression.charAt(index);
            if( currentChar == ')'  ){
                if( ++openedBracketsAmnt == 1 ){
                    lastRightBracketIndex = index;
                }
            }else if( currentChar == '('  ){
                if( --openedBracketsAmnt == 0 ){
                    expression = expression.substring( 0 , index ) +
                            calculate( expression.substring( index + 1, lastRightBracketIndex ) ) +
                            expression.substring( lastRightBracketIndex + 1 );
                }
            }
            index--;
        }
        return expression;
    }
 
 
    // Example: 1*8/4 ~ 3*16*11/24 + 4 * 6 should return 2 ~ 22 + 24
    public String calcMultDivSequences( String expression ) throws Exception {
        int index = expression.length() - 1;
        int lastPlusOrMinusIndex = expression.length();
        char currentChar;
        while( -1 < index  ){
            currentChar = expression.charAt(index);
            if( currentChar == '+' || currentChar == '~'  ){
                expression = expression.substring( 0 , index + 1 ) +
                            calcSequence( expression.substring( index + 1, lastPlusOrMinusIndex ) ) +
                            expression.substring( lastPlusOrMinusIndex );
                lastPlusOrMinusIndex = index;
            }
            index--;
        }
        return expression;
    }
 
    // Important !1!  input string  should contain operations with equal priority
    private String calcSequence( String expression ){
        int index = 0;
        double result = 1.0;
        char prevSign = '*';
        int prevSignIndex = -1;
        char currentChar;
        while( true ){
            currentChar = expression.charAt( index );
            if( currentChar == '+' || currentChar == '~' || currentChar == '*' || currentChar == '/' ){
                result = calc( result,
                        Double.parseDouble(expression.substring( prevSignIndex + 1, index )),
                        prevSign
                    );
                prevSign = currentChar;
                prevSignIndex = index;
            }else if( index == expression.length() - 1 ){
                result = calc( result,
                        Double.parseDouble(expression.substring( prevSignIndex + 1, index + 1 )),
                        prevSign
                    );
                break;
            }
            index++;
        }
        return Integer.toString( (int)result );
    }
 
    // Just calc
    private double calc( double result,double number, char sign ){
        switch (sign){
            case '+':{
                result += number;
                break;
            }case '~':{
                result -= number;
                break;
            }case '*':{
                result *= number;
                break;
            }case '/':{
                result /= number;
                break;
            }default :{
                System.out.println( "Wrong Sign" );
            }
        }
        return (int)result;
    }
}