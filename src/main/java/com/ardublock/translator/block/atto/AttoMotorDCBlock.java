package com.ardublock.translator.block.atto;


import com.ardublock.core.Context;
import java.util.ResourceBundle;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class AttoMotorDCBlock extends TranslatorBlock
{
	public AttoMotorDCBlock(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException , SubroutineNotDeclaredException
	{
	   
	    TranslatorBlock tb_dir = this.getRequiredTranslatorBlockAtSocket(0);
	    String ret_dir = tb_dir.toCode();
		String aux_ret_dir = "int motor_dir =";
		aux_ret_dir = aux_ret_dir + tb_dir.toCode();
		aux_ret_dir = aux_ret_dir + ";";
		
	   
	    TranslatorBlock tb_esq = this.getRequiredTranslatorBlockAtSocket(1);
	    String ret_esq = tb_esq.toCode();
		String aux_ret_esq = "int motor_esq =";
		aux_ret_esq = aux_ret_esq + tb_esq.toCode();
		aux_ret_esq = aux_ret_esq + ";";
				
		TranslatorBlock movimentBlock = this.getRequiredTranslatorBlockAtSocket(2);
		String ret_1 = movimentBlock.toCode();
				
		TranslatorBlock timeBlock = this.getRequiredTranslatorBlockAtSocket(3);
			String time_segundos_string = timeBlock.toCode();
			int time_int = Integer.parseInt(time_segundos_string);
			time_int = time_int*1000;
			String time_segundos = ""+(time_int);
			
		
		
		/*######################################################################
		###########constantes para o carrinho ir para frente ou para trás#######
		######################################################################*/
		
			String pino_frente = "HIGH";
			String pino_re = "LOW";		
			String pino5 = "5";
			String pino6 = "6";
			String veloc_maxima = "255";
			String veloc_virada = "100";
			
		/*##############################################################################
		#########Comparações para ver se vai para (Frente,Re,Direita,Esquerda)##########
		###############################################################################*/
		
		String Frente = "Frente";
		if(movimentBlock.toCode().equals(ret_1) == movimentBlock.toCode().equals("1")){
			translator.addDefinitionCommand(aux_ret_dir);
			translator.addDefinitionCommand(aux_ret_esq);
	    	translator.addSetupCommand("pinMode(motor_dir, OUTPUT);\n  pinMode(motor_esq, OUTPUT);");
			String ret_frente = "digitalWrite(motor_dir,"+  pino_frente +");\n digitalWrite(motor_esq,"+ pino_frente +");\n  analogWrite("+ pino5 + "," + veloc_maxima +");\n analogWrite("+ pino6 + "," + veloc_maxima + ");\n delay("+time_segundos+");";
			return codePrefix + ret_frente + codeSuffix;
		}
		
		
		String Re = "Re";
		if(movimentBlock.toCode().equals(ret_1) == movimentBlock.toCode().equals("2")){
			translator.addDefinitionCommand(aux_ret_dir);
			translator.addDefinitionCommand(aux_ret_esq);
	    	translator.addSetupCommand("pinMode(motor_dir, OUTPUT);\n  pinMode(motor_esq, OUTPUT);");
			String ret_re = "digitalWrite(motor_dir,"+ pino_re +");\n digitalWrite(motor_esq ,"+ pino_re +");\n  analogWrite("+ pino5 + "," + veloc_maxima +");\n analogWrite("+ pino6 + "," + veloc_maxima +");\n delay("+time_segundos+");";
			return codePrefix + ret_re + codeSuffix;
		}
		
		String Direita = "Direita";
			if(movimentBlock.toCode().equals(ret_1) == movimentBlock.toCode().equals("3")){
				if(tb_dir.toCode().equals(ret_dir) == tb_dir.toCode().equals("10")){
					translator.addDefinitionCommand(aux_ret_dir);
					translator.addDefinitionCommand(aux_ret_esq);
	    			translator.addSetupCommand("pinMode(motor_dir, OUTPUT);\n  pinMode(motor_esq, OUTPUT);");
					String ret_direita = "digitalWrite(motor_dir," + pino_frente +");\n digitalWrite(motor_esq,"+pino_frente+");\n  analogWrite("+ pino5 + "," + veloc_virada +");\n analogWrite("+ pino6 +"," + veloc_maxima+");\n delay("+time_segundos+");";
					return codePrefix + ret_direita + codeSuffix;
				}
				if(tb_dir.toCode().equals(ret_dir) == tb_dir.toCode().equals("11")){
					translator.addDefinitionCommand(aux_ret_dir);
					translator.addDefinitionCommand(aux_ret_esq);
	    			translator.addSetupCommand("pinMode(motor_dir, OUTPUT);\n  pinMode(motor_esq, OUTPUT);");
					String ret_direita = "digitalWrite(motor_dir,"+pino_frente+");\n digitalWrite(motor_esq,"+pino_frente+");\n  analogWrite("+pino5+ "," +veloc_maxima+");\n analogWrite("+pino6+","+veloc_virada+");\ndelay("+time_segundos+");";
					return codePrefix + ret_direita + codeSuffix;
				}
			}
		
		String Esquerda = "Esquerda";
		if(movimentBlock.toCode().equals(ret_1) == movimentBlock.toCode().equals("4")){
			if(tb_esq.toCode().equals(ret_esq) == tb_esq.toCode().equals("11")){
				translator.addDefinitionCommand(aux_ret_dir);
				translator.addDefinitionCommand(aux_ret_esq);
	    		translator.addSetupCommand("pinMode(motor_dir, OUTPUT);\n  pinMode(motor_esq, OUTPUT);");
				String ret_esquerda = "digitalWrite(motor_dir,"+pino_frente+");\n digitalWrite(motor_esq,"+pino_frente+");\n  analogWrite("+pino5+","+veloc_maxima+");\n analogWrite("+pino6+","+veloc_virada+");\ndelay("+time_segundos+");";
				return codePrefix + ret_esquerda + codeSuffix;
				}
			if(tb_esq.toCode().equals(ret_esq) == tb_esq.toCode().equals("10")){
				translator.addDefinitionCommand(aux_ret_dir);
				translator.addDefinitionCommand(aux_ret_esq);
	    		translator.addSetupCommand("pinMode(motor_dir, OUTPUT);\n  pinMode(motor_esq, OUTPUT);");
				String ret_esquerda = "digitalWrite(motor_dir,"+pino_frente+");\n digitalWrite(motor_esq,"+pino_frente+");\n  analogWrite("+pino5+","+veloc_virada+");\n analogWrite("+pino6+","+veloc_maxima+");\ndelay("+time_segundos+");";
				return codePrefix + ret_esquerda + codeSuffix;
			}
		}
		
		//Parado
		if(movimentBlock.toCode().equals(ret_1) == movimentBlock.toCode().equals("5")){
			translator.addDefinitionCommand(aux_ret_dir);
			translator.addDefinitionCommand(aux_ret_esq);
	    	translator.addSetupCommand("pinMode(motor_dir, OUTPUT);\n  pinMode(motor_esq, OUTPUT);");
			String ret_frente = "digitalWrite(motor_dir,"+  pino_frente +");\n digitalWrite(motor_esq,"+ pino_frente +");\n  analogWrite("+ pino5 + ", 0 );\n analogWrite("+ pino6 + ", 0 );\n delay("+time_segundos+");";
			return codePrefix + ret_frente + codeSuffix;
		}
			
	
		return codePrefix + "" + codeSuffix;
		
	}
}
