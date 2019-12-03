package tech.laihz.treex.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TreexTypeFile {
  public static final int MUSIC_TREEX = 100;
  public static final int MOVIE_TREEX = 200;
  public static final int PHOTO_TREEX = 300;
  public static final int DOCUMENT_TREEX = 400;
  public static final int OTHERS_TREEX = 500;
  private int type;

  TreexTypeFile(int type) {
    this.type = type;
  }

}
