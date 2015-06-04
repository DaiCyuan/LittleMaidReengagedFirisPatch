package net.blacklab.lib;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigList {
	private List<ConfigKey> list;
	
	/**
	 * Config�t�@�C���̐ݒ���e�̈ꗗ���i�[���邽�߂�ConfigList�̐V�����C���X�^���X�𐶐����܂��B
	 * ������s�킸��ConfigList�𑀍삷���NullPointerException���������܂��B
	 * �C���X�^���X�͒ʏ�@Mod�A�m�e�[�V�����̂����N���X��static�t�B�[���h�Ƃ��ėp�ӂ��Ă��������B
	 */
	public ConfigList(){
		list = new ArrayList<ConfigKey>();
	}
	
	//get
	/**
	 * �w�肵��Key�ɍ��v����ConfigKey��Ԃ��܂��B
	 * @param par1 Key
	 * @exception IllegalStateException �w�肵��Key��������Ȃ��ꍇ�ɃX���[����܂��B
	 **/
	public ConfigKey getConfigByKey(String par1){
		if(!isExistKey(par1)) throw new IllegalStateException("Requested key is not found");
		for(ConfigKey key : list){
			if(key.getKey().equals(par1)) return key;
		}
		throw new IllegalStateException("Requested key is not found");
	}

	/**
	 * �w�肵��Key��ConfigList���ɑ��݂��邩�m�F���܂��B���݂��Ă����true��Ԃ��܂��B
	 * ������null�̏ꍇ�Afalse��Ԃ��܂��B
	 * @param par1 Key
	 */
	public boolean isExistKey(String par1){
		if(par1==null) return false;
		for(ConfigKey key : list){
			if(key.getKey().equals(par1)) return true;
		}
		return false;
	}
	
	private int indexOf(String par1){
		int i = 0;
		for(ConfigKey key:list){
			if(key.getKey().equals(par1)) return i;
			i++;
		}
		return -1;
	}

	//add
	/**
	 * ConfigKey��ConfigList�ɒǉ����܂��B
	 * @param par1 Key
	 * @param par2 �ݒ肳���l
	 * @exception IllegalStateException Key�����łɑ��݂���ꍇ�ɃX���[����܂��B
	 * **/
	public void putConfig(ConfigKey par1){
		if(isExistKey(par1.getKey())) remove(par1.getKey());
		list.add(par1);
	}

	/**
	 * ������̒l������ConfigKey��ConfigList�ɒǉ����܂��B
	 * @param par1 Key
	 * @param par2 �ݒ肳���l
	 * @exception IllegalStateException Key�����łɑ��݂���ꍇ�ɃX���[����܂��B
	 * **/
	public void putString(String par1,String par2){
		putConfig(new ConfigKey(par1,par2));
	}
	
	/**
	 * �����̒l������ConfigKey��ConfigList�ɒǉ����܂��B
	 * @param par1 key
	 * @param par2 �ݒ肳���l
	 * @exception IllegalStateException Key�����łɑ��݂���ꍇ�ɃX���[����܂��B
	 */
	public void putInt(String par1,int par2){
		putConfig(new ConfigKey(par1,String.valueOf(par2)));
	}

	/**
	 * �����̒l������ConfigKey��ConfigList�ɒǉ����܂��B
	 * @param par1 key
	 * @param par2 �ݒ肳���l
	 * @exception IllegalStateException Key�����łɑ��݂���ꍇ�ɃX���[����܂��B
	 */
	public void putFloat(String par1,float par2){
		putConfig(new ConfigKey(par1,String.valueOf(par2)));
	}

	/**
	 * �^�U�̒l������ConfigKey��ConfigList�ɒǉ����܂��B
	 * @param par1 key
	 * @param par2 �ݒ肳���l
	 * @exception IllegalStateException Key�����łɑ��݂���ꍇ�ɃX���[����܂��B
	 */
	public void putBoolean(String par1,boolean par2){
		putConfig(new ConfigKey(par1,String.valueOf(par2)));
	}
	
	/**
	 * �w�肵��Key�ɍ��v����ConfigKey���Q�Ƃ�String�Ƃ��Ēl��Ԃ��܂��B
	 * ConfigKey�����݂��Ȃ��ꍇ�A�Y������Key�ɑ������̓��e��ݒ肵�đ������̓��e�����̂܂ܕԂ��܂��B
	 * @param par1 Key
	 * @param defaultvalue �f�t�H���g�ŕԂ�l
	 * @exception IllegalStateException �w�肵��Key��������Ȃ��ꍇ�ɃX���[����܂��B
	 **/
	public String getString(String par1, String defaultvalue){
		if(isExistKey(par1)){
			return getConfigByKey(par1).getValue();
		}else{
			putString(par1,defaultvalue);
			return defaultvalue;
		}
	}
	
	private void remove(String par1){
		if(isExistKey(par1)) list.remove(indexOf(par1));
	}
	
	/**
	 * �w�肵��Key�ɍ��v����ConfigKey���Q�Ƃ�int�Ƃ��Ēl��Ԃ��܂��B
	 * ConfigKey�����݂��Ȃ��ꍇ�A�Y������Key�ɑ������̓��e��ݒ肵�đ������̓��e�����̂܂ܕԂ��܂��B
	 * @param par1 Key
	 * @param defaultvalue �f�t�H���g�ŕԂ�l
	 * @throws NumberFormatException ConfigKey�̎擾���ł������l�������l�łȂ��ꍇ�ɃX���[����܂��B
	 * @exception IllegalStateException �w�肵��Key��������Ȃ��ꍇ�ɃX���[����܂��B
	 **/
	public int getInt(String par1, int defaultvalue) throws NumberFormatException{
		System.out.println("SSS="+par1);
		if(isExistKey(par1)){
			return Integer.parseInt(getConfigByKey(par1).getValue());
		}else{
			putInt(par1,defaultvalue);
			return defaultvalue;
		}
	}
	
	/**
	 * �w�肵��Key�ɍ��v����ConfigKey���Q�Ƃ�float�Ƃ��Ēl��Ԃ��܂��B
	 * ConfigKey�����݂��Ȃ��ꍇ�A�Y������Key�ɑ������̓��e��ݒ肵�đ������̓��e�����̂܂ܕԂ��܂��B
	 * @param par1 Key
	 * @param defaultvalue �f�t�H���g�ŕԂ�l
	 * @throws NumberFormatException ConfigKey�̎擾���ł������l�������l�łȂ��ꍇ�ɃX���[����܂��B
	 * @exception IllegalStateException �w�肵��Key��������Ȃ��ꍇ�ɃX���[����܂��B
	 **/
	public float getFloat(String par1, float defaultvalue) throws NumberFormatException{
		if(isExistKey(par1)){
			return Float.parseFloat(getConfigByKey(par1).getValue());
		}else{
			putFloat(par1,defaultvalue);
			return defaultvalue;
		}
	}
	
	/**
	 * �w�肵��Key�ɍ��v����ConfigKey���Q�Ƃ�boolean�Ƃ��Ēl��Ԃ��܂��B
	 * ConfigKey�����݂��Ȃ��ꍇ�A�Y������Key�ɑ������̓��e��ݒ肵�đ������̓��e�����̂܂ܕԂ��܂��B
	 * @param par1 Key
	 * @param defaultvalue �f�t�H���g�ŕԂ�l
	 * @exception IllegalStateException �w�肵��Key��������Ȃ��ꍇ�ɃX���[����܂��B
	 **/
	public boolean getBoolean(String par1, boolean defaultvalue){
		if(isExistKey(par1)){
			return Boolean.parseBoolean(getConfigByKey(par1).getValue());
		}else{
			putBoolean(par1,defaultvalue);
			return defaultvalue;
		}
	}
	
	/**
	 * Config�t�@�C����ǂݏo���܂��B
	 * "(�������Ŏw�肵�����O).cfg"�̏���ConfigList�Ɋi�[���܂��B
	 * ���̃��\�b�h��PreInitialize�C�x���g���ŌĂяo�����K�v������܂��B
	 * config�t�@�C������key�Ɛݒ�l�̋�؂蕶����"="�ł��B
	 * @param configFileName �R���t�B�O�t�@�C���̖��O(.cfg�������ċL�q���܂�)
	 * @param event EventHandler�ɓn�����FMLPreInitializationEvent
	 * @throws IOException  ���炩�̗��R��config�̃��[�h�Ɏ��s�����ꍇ�ɃX���[����܂��B
	 */
	public void loadConfig(String configFileName,FMLPreInitializationEvent event) throws IOException{
		File file = new File(event.getModConfigurationDirectory(), configFileName+".cfg");
		loadConfig(file);
	}

	/**
	 * Config�t�@�C����ǂݏo���܂��B
	 * ���̃��\�b�h�ł̓t�@�C�������w�肹��FMLPreInitializationEvent#getSuggestedConfigurationFile()�̎w���t�@�C�����g�p���܂��B
	 * ���̃��\�b�h��PreInitialize�C�x���g���ŌĂяo�����K�v������܂��B
	 * config�t�@�C������key�Ɛݒ�l�̋�؂蕶����"="�ł��B
	 * @param event EventHandler�ɓn�����FMLPreInitializationEvent
	 * @throws IOException  ���炩�̗��R��config�̃��[�h�Ɏ��s�����ꍇ�ɃX���[����܂��B
	 */
	public void loadConfig(FMLPreInitializationEvent event) throws IOException{
		loadConfig(event.getSuggestedConfigurationFile());
	}
	
	/**
	 * Config�t�@�C����ǂݏo���܂��B
	 * ���̃��\�b�h�ł�File�𒼐ڎw�肵�܂��B
	 * config�t�@�C������key�Ɛݒ�l�̋�؂蕶����"="�ł��B
	 * @param file File�I�u�W�F�N�g
	 * @throws IOException ���炩�̗��R��config�̃��[�h�Ɏ��s�����ꍇ�ɃX���[����܂��B
	 */
	public void loadConfig(File file) throws IOException{
		if(file.exists()&&file.canRead()){
			for(String line:Files.readAllLines(file.toPath(), Charset.forName("UTF-8"))){
				try{
					if(!line.contains("=")) continue;
					String[] sp = line.split("=",2);
					putString(sp[0],sp[1]);
				}catch(NullPointerException e){
					
				}
			}
		}
	}
	
	/**
	 * Config�t�@�C���������o���܂��B
	 * "(�������Ŏw�肵�����O).cfg"�̏���ConfigList�Ɋi�[���܂��B
	 * ���̃��\�b�h��PreInitialize�C�x���g���ŌĂяo�����K�v������܂��B
	 * config�t�@�C������key�Ɛݒ�l�̋�؂蕶����"="�ł��B
	 * @param configFileName �R���t�B�O�t�@�C���̖��O(.cfg�������ċL�q���܂�)
	 * @param event EventHandler�ɓn�����FMLPreInitializationEvent
	 * @throws IOException  ���炩�̗��R��config�̃Z�[�u�Ɏ��s�����ꍇ�ɃX���[����܂��B
	 */
	public void saveConfig(String configFileName,FMLPreInitializationEvent event) throws IOException{
		File file = new File(event.getModConfigurationDirectory(), configFileName+".cfg");
		saveConfig(file);
	}
	
	/**
	 * Config�t�@�C���������o���܂��B
	 * ���̃��\�b�h�ł̓t�@�C�������w�肹��FMLPreInitializationEvent#getSuggestedConfigurationFile()�̎w���t�@�C�����g�p���܂��B
	 * ���̃��\�b�h��PreInitialize�C�x���g���ŌĂяo�����K�v������܂��B
	 * config�t�@�C������key�Ɛݒ�l�̋�؂蕶����"="�ł��B
	 * @param event EventHandler�ɓn�����FMLPreInitializationEvent
	 * @throws IOException  ���炩�̗��R��config�̃Z�[�u�Ɏ��s�����ꍇ�ɃX���[����܂��B
	 */
	public void saveConfig(FMLPreInitializationEvent event) throws IOException{
		saveConfig(event.getSuggestedConfigurationFile());
	}

	/**
	 * Config�t�@�C���������o���܂��B���̃��\�b�h�ł�File�𒼐ڎw�肵�܂��B
	 * @param file File�I�u�W�F�N�g
	 * @throws IOException ���炩�̗��R��config�̃Z�[�u�Ɏ��s�����ꍇ�ɃX���[����܂��B
	 */
	public void saveConfig(File file) throws IOException{
		List<CharSequence> temp = new ArrayList<CharSequence>();
		for(ConfigKey k:list){
			temp.add(k.getKey()+"="+k.getValue());
		}
		Files.write(file.toPath(), temp, Charset.forName("UTF-8"));
	}
}
