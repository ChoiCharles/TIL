package ch08.sec13;

// java 15 부터 가능
public sealed interface InterfaceA permits InterfaceB {
	void methodA();
}
