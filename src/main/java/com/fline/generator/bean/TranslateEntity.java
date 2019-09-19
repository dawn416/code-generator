package com.fline.generator.bean;

import java.util.List;

/**
 * @since 2019年9月19日 上午8:22:27
 *
 */
public class TranslateEntity {
	private String type;
	private int errorCode;
	private int elapsedTime;
	private List<List<Entity>> translateResult;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public List<List<Entity>> getTranslateResult() {
		return translateResult;
	}

	public void setTranslateResult(List<List<Entity>> translateResult) {
		this.translateResult = translateResult;
	}

	public class Entity {
		private String src;
		private String tgt;

		public String getSrc() {
			return src;
		}

		public void setSrc(String src) {
			this.src = src;
		}

		public String getTgt() {
			return tgt;
		}

		public void setTgt(String tgt) {
			this.tgt = tgt;
		}
	}
}
