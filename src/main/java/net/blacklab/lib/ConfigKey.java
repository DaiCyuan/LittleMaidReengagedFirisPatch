package net.blacklab.lib;

public class ConfigKey {
	private String key;
	private String value;
	
	/**
	 * Config�̊e�ݒ荀�ڂ��i�[����ConfigKey�̐V�����C���X�^���X�𐶐����܂��BKey,�ݒ�l����String�^�Ŋi�[����܂��B
	 * @param par1 key
	 * @param par2 �ݒ肷��l
	 */
	public ConfigKey(String par1, String par2){
		key = par1;
		value = par2;
	}

	/**
	 * key,value���ɖ���`��ConfigKey�̃C���X�^���X�𐶐����܂��B
	 */
	public ConfigKey(){
		this("","");
	}
	
	/**
	 * Key���擾���܂��Bnull�ł���΁A��̕������Ԃ��܂��B
	 * @return Key
	 */
	public String getKey(){
		if(key!=null) return key; else return "";
	}
	
	/**
	 * Key��ύX���܂��B
	 * @param par1 �ύX���Key
	 */
	public ConfigKey setKey(String par1){
		key = par1;
		return this;
	}
	
	//get
	/**
	 * �ݒ�l��Ԃ��܂��B
	 * @return �ݒ�l
	 */
	public String getValue(){
		return value;
	}
	
	/**
	 * �ݒ�l��ύX���܂��B
	 * @param par1 �ύX��̐ݒ�l
	 */
	public ConfigKey setValue(String par1){
		value = par1;
		return this;
	}
}