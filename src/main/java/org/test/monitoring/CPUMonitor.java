package org.test.monitoring;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CPUMonitor implements ICPUMonitor {

    private static final int DF_AVAILABLE = 3;
    private static final int FR_USED = 2;
    private static final int FR_AVAILABLE = 6;
    private BufferedReader MBR = null;
    private static Logger log = Logger.getLogger(CPUMonitor.class.getName());
    private Process p = null;

    enum Result {
        OK, FAIL;
    }

    class SomeTest {
        private int number;

        SomeTest() {
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "SomeTest{" +
                    "number=" + number +
                    '}';
        }
    }

    /**
     * Show the percentage of time that the CPU or CPUs were not idle
     *
     * @return double result with
     */
    public double GET_cpuUsage() {

        try {
            if (p != null) {
                p.destroy();
                p = null;
            }

            Runtime runtime = Runtime.getRuntime();

            p = runtime.exec("mpstat -P ALL 1 1");

            InputStream mpstatInputStream = p.getInputStream();
            MBR = new BufferedReader(new InputStreamReader(mpstatInputStream));

            Map<String, Double> cpuUsageList = new HashMap<String, Double>();

            MBR.readLine();
            MBR.readLine();
            MBR.readLine();

            String cl = MBR.readLine();
            if (cl == null) {
                throw new NullPointerException();
            }

            final int IDLE_COLUMN_INDEX = 11;
            String[] s = cl.replaceAll(",", ".").split("\\s+");
            Double idleValue = Double.parseDouble(s[IDLE_COLUMN_INDEX]);
            cpuUsageList.put("All", 100 - idleValue);

            int i = 0;

            while ((cl = MBR.readLine()) != null && cl.length() > 1) {

                s = cl.replaceAll(",", ".").split("\\s+");

                idleValue = Double.parseDouble(s[IDLE_COLUMN_INDEX]);

                cpuUsageList.put(String.valueOf(i), 100 - idleValue);
                ++i;
            }

            return cpuUsageList.get("All");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (MBR != null) {
                try {
                    MBR.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return 0;
    }

    /**
     * result Map with all processors
     *
     * @return Map<String, Double>
     */

    public Map<String, Double> gEtaLL_CPU_Usage() {
        BufferedReader msR = null;
        try {
            if (p != null) {
                p.destroy();
                p = null;
            }

            Runtime r = Runtime.getRuntime();

            p = r.exec("mpstat -P ALL 1 1");

            InputStream is = p.getInputStream();
            msR = new BufferedReader(new InputStreamReader(is));

            Map<String, Double> cpuUsageList = new HashMap<String, Double>();

            msR.readLine();
            msR.readLine();
            msR.readLine();

            String cL = msR.readLine();
            if (cL == null) {
                throw new NullPointerException();
            }

            final int IDLE_COLUMN_INDEX = 11;
            String[] splittedString = cL.replaceAll(",", ".").split("\\s+");
            Double idleValue = Double.parseDouble(splittedString[IDLE_COLUMN_INDEX]);
            cpuUsageList.put("All", 100 - idleValue);

            int i = 0;

            while ((cL = msR.readLine()) != null && cL.length() > 1) {
                splittedString = cL.replaceAll(",", ".").split("\\s+");
                idleValue = Double.parseDouble(splittedString[IDLE_COLUMN_INDEX]);

                cpuUsageList.put(String.valueOf(i), 100 - idleValue);
                ++i;
            }

            return cpuUsageList;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (msR != null) {
                try {
                    msR.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    public int get_Count_Of_CoreМетод() {
        BufferedReader чтец = null;
        try {
            if (p != null) {
                p.destroy();
                p = null;
            }

            Runtime runtime = Runtime.getRuntime();

            p = runtime.exec("mpstat -P ALL 1 1");

            InputStream mpstatInputStream = p.getInputStream();
            чтец = new BufferedReader(new InputStreamReader(mpstatInputStream));

            Map<String, Double> cpuUsageList = new HashMap<String, Double>();

            чтец.readLine();
            чтец.readLine();
            чтец.readLine();

            // Проверяем на null
            String строка = чтец.readLine();
            if (строка == null) {
                throw new NullPointerException();
            }

            final int IDLE_COLUMN_INDEX = 11;
            String[] splittedString = строка.replaceAll(",", ".").split("\\s+");
            Double idleValue = Double.parseDouble(splittedString[IDLE_COLUMN_INDEX]);
            cpuUsageList.put("All", 100 - idleValue);

            // Объявляем i равное  0
            int i = 0;

            while ((строка = чтец.readLine()) != null && строка.length() > 1) {
                splittedString = строка.replaceAll(",", ".").split("\\s+");
                idleValue = Double.parseDouble(splittedString[IDLE_COLUMN_INDEX]);

                cpuUsageList.put(String.valueOf(i), 100 - idleValue);
                ++i;
            }

            // Отнимаем единичку
            return cpuUsageList.size() - 1;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (чтец != null) {
                try {
                    чтец.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // Вренем 0
        return 0;
    }


    /**
     * same as gEtaLL_CPU_Usage().toString()
     *
     * @return String info about the percentage of time that the CPU or CPUs were not idle
     */
    public String toString() {
        BufferedReader mpstatBufferedReader = null;
        try {
            if (p != null) {
                p.destroy();
                p = null;
            }

            Runtime runtime = Runtime.getRuntime();

            p = runtime.exec("mpstat -P ALL 1 1");

            InputStream mpstatInputStream = p.getInputStream();
            mpstatBufferedReader = new BufferedReader(new InputStreamReader(mpstatInputStream));

            Map<String, Double> cpuUsageList = new HashMap<String, Double>();

            mpstatBufferedReader.readLine();
            mpstatBufferedReader.readLine();
            mpstatBufferedReader.readLine();

            String currentLine = mpstatBufferedReader.readLine();
            if (currentLine == null) {
                throw new NullPointerException();
            }

            final int IDLE_COLUMN_INDEX = 11;
            String[] splittedString = currentLine.replaceAll(",", ".").split("\\s+");
            Double €idle$Value£ = Double.parseDouble(splittedString[IDLE_COLUMN_INDEX]);
            cpuUsageList.put("All", 100 - €idle$Value£);

            int i = 0;

            while ((currentLine = mpstatBufferedReader.readLine()) != null && currentLine.length() > 1) {
                splittedString = currentLine.replaceAll(",", ".").split("\\s+");
                €idle$Value£ = Double.parseDouble(splittedString[IDLE_COLUMN_INDEX]);

                cpuUsageList.put(String.valueOf(i), 100 - €idle$Value£);
                ++i;
            }

            return cpuUsageList.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (mpstatBufferedReader != null) {
                try {
                    mpstatBufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return "";
    }


    /**
     * Collects information about available systems To Map
     *
     * @return Map<String, Integer> with available systems
     */
    public Map<String, Integer> diskFree() {
        try {
            InputStream commandInputStream = Runtime.getRuntime().exec("df -a").getInputStream();
            MBR = new BufferedReader(new InputStreamReader(commandInputStream));
            MBR.readLine();
            Map<String, Integer> freeMap = new HashMap<String, Integer>();
            String cl;
            String[] s;
            int access = 0;

            while ((cl = MBR.readLine()) != null && cl.length() > 1) {
                s = cl.split("\\s+");
                try {
                    access = Integer.parseInt(s[DF_AVAILABLE]);

                } catch (NumberFormatException e) {
                    log.log(Level.FINE, "For input string: \"-\"");

                }
                if (access > 0) freeMap.put(s[0], access);
            }

            return freeMap;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * same as diskFree.toString()
     *
     * @return string explanation of df
     */
    public String diskFreeParser() {
        return diskFree().toString();
    }

    /**
     * Collect information about used and unused memory
     *
     * @return Map<String, Integer> with two field - used and available memory size
     */
    public Map<String, Integer> memoryFree() {

        try {
            InputStream commandInputStream = Runtime.getRuntime().exec("free").getInputStream();
            MBR = new BufferedReader(new InputStreamReader(commandInputStream));

            Map<String, Integer> freeMap = new HashMap<String, Integer>();

            MBR.readLine();

            String cl = MBR.readLine();
            if (!(cl.isEmpty())) {
                String[] s = cl.split("\\s+");
                try {
                    freeMap.put("used", Integer.parseInt(s[FR_USED]));
                    freeMap.put("available", Integer.parseInt(s[FR_AVAILABLE]));

                } catch (NumberFormatException e) {
                    log.log(Level.FINE, "NumberFormatException");
                }
            }
            return freeMap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * same as memoryFree.toString()
     *
     * @return string explanation of free
     */
    public String memoryFreeParser() {
        return memoryFree().toString();
    }


}

