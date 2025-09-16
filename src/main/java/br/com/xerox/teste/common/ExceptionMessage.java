package br.com.xerox.teste.common;

import java.util.Date;
import java.util.List;

public record ExceptionMessage(Date timestamp, String erro, String mensagem, List<FieldMessage> trace) {

}
