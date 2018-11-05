import java.io.*;
import java.lang.reflect.WildcardType;

public class MyUtility {
    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            /**
             * Поиск всех локальных дисков
             */

            if (args[i] != null && args[i].equals("--readDrives")) {
                File[] roots = File.listRoots();
                for (File file : roots) {
                    System.out.println(file);
                }
            }
            /**
             * Поиск файла по пути и имени
             */
            if (args[i].equals("--find")) {
                File f1 = new File(args[i + 1]);
                final String fileName = f1.getName();
                String path = f1.getParent();

                File f = new File(path);
                File[] matchingFiles = f.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.contains(fileName);
                    }
                });
                if (matchingFiles != null && matchingFiles.length > 0) {
                    for (File item : matchingFiles) {
                        System.out.println(item);

                        /**
                         * Добавление строки в файл
                         */
                        if (args.length == 3) {
                            System.out.println("Добавление строки");

                            try {
                                File mFile = new File(String.valueOf(item));
                                FileInputStream fileIn = new FileInputStream(item);
                                BufferedReader br = new BufferedReader(new InputStreamReader((fileIn)));
                                String result = "";
                                String line;
                                while ((line = br.readLine()) != null) {
                                    result = result + line;
                                }
                                result = args[i + 2] + "\r\n" + result;
                                FileOutputStream fileOut = new FileOutputStream((mFile));
                                fileOut.write(result.getBytes());
                                fileOut.flush();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else System.out.println("File not found");
            }

        }
    }
}
