package gateway;

import autenticacao.InterfaceAutenticacao;
import data.InterfaceVeiculoCRUD;

import java.rmi.Remote;

public interface InterfaceGateway extends Remote, InterfaceAutenticacao, InterfaceVeiculoCRUD {

}
