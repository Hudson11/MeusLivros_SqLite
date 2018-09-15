package br.edu.ufrn.meuslivros_part1.classes;

import android.provider.BaseColumns;

public class LivroContrato {

    public LivroContrato(){}

    public static class LivroEntry implements BaseColumns{
        public static String TABLE_NAME = "livro";
        public static String TITULO = "titulo";
        public static String AUTOR = "autor";
        public static String ANO = "ano";
        public static String NOTA = "nota";
    }
}
