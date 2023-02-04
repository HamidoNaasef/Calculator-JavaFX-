
package calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    TextField screen;
    
    
    boolean hasPoint = false;
    boolean hasJustOp = false;
    boolean hasStoredValue = false;
    
    String operator = "";
    
    double num1 = 0, num2 = 0;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    
    @FXML
    private void numberAction(ActionEvent event) {
        if(hasJustOp){
            screen.setText(((Button)event.getSource()).getText());
            hasJustOp = false;
            
        }else{
            screen.setText(screen.getText() + ((Button)event.getSource()).getText()); 
        }
    }
    
    @FXML
    private void operatorAction(ActionEvent event) throws Exception {
        if(screen.getText().length() != 0){
            if(screen.getText().equals(".")){
                screen.setText(""); 
            }
            if (".".equals(screen.getText().charAt(screen.getText().length()-1)) ){
                screen.setText(screen.getText().substring(0, screen.getText().length()-2));
            }
            if( !((Button)event.getSource()).getText().equals("=") ){
                
                //operators +,-,/,*
                
                if(hasJustOp){
                    operator = ((Button)event.getSource()).getText();
                }else{
                    hasPoint = false;
                    if(hasStoredValue){
                        num2 = Double.parseDouble(screen.getText());

                        String calcResult = calcTwoNumbers(num1, num2, operator);
                        if(!calcResult.equals("NaN") ){
                            screen.setText(calcResult);
                        }else{
                            screen.setText("Error!!");
                            Thread.sleep(2500);
                            screen.setText("");
                        }
                        num1 = Double.parseDouble(calcResult);
                        operator = ((Button)event.getSource()).getText();
                        hasJustOp = true;


                    }else{
                        num1 = Double.parseDouble(screen.getText());
                        hasStoredValue = true;
                        hasJustOp = true;
                        operator = ((Button)event.getSource()).getText();

                    }
                }
                                
            //if operator is '='
            }else{
                if(hasStoredValue){
                    if(screen.getText().isEmpty()){
                        screen.setText(String.valueOf(num1) );

                    }else{
                        num2 = Double.parseDouble(screen.getText());


                        String calcResult = calcTwoNumbers(num1, num2, operator);
                        if(!calcResult.equals("NaN") ){
                            screen.setText(calcResult);
                        }else{
                            screen.setText("Error!!");
                            Thread.sleep(2500);
                            screen.setText("");
                        }
                    }
                }
                //clearing the memory
                hasPoint = false;
                hasJustOp = false;
                hasStoredValue = false;
                operator = "";
                num1 = 0;
                num2 = 0;
            }
            //end of '='
        }
    }
    
    @FXML
    private void clearAll() {
        screen.setText("");
        hasPoint = false;
        hasJustOp = false;
        hasStoredValue = false;

        operator = "";

        num1 = 0;
        num2 = 0;
    }
    
    @FXML
    private void backSpace() {
        if(!hasJustOp){    
            if(screen.getText().length() != 0){
                String oldScreen = screen.getText();
                String newScreen = oldScreen.substring(0, screen.getText().length()-1);
                screen.setText(newScreen);

                if( ".".equals(oldScreen.charAt(oldScreen.length()-1)) ){ 
                    hasPoint = false;
                }
            }
        }
    }
    
    @FXML
    private void pointhandler(){
        if(!hasPoint){
            hasPoint = true;
            screen.setText(screen.getText() + "." ); 
        }
    }
    
    private String calcTwoNumbers(double number1, double number2, String operator){
        double result = 0;
        switch(operator){
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if(number2 != 0){
                    result = number1 / number2;
                }else{
                    return "NaN";
                }
                break;
        
        }
        return String.valueOf(result);
    }
   
    
}
