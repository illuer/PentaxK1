package piao.yezi.pentaxk1.bean;

import java.util.List;

/**
 * Created by yezi on 05/08/2017.
 */

public class GetPhotosBean extends BaseResponseBean {

    private List<DirsBean> dirs;

    public List<DirsBean> getDirs() {
        return dirs;
    }

    public void setDirs(List<DirsBean> dirs) {
        this.dirs = dirs;
    }

    public static class DirsBean {
        /**
         * name : 205_0204
         * files : ["17016385.JPG","17016385.DNG","17016386.JPG","17016386.DNG","17016389.JPG","17016389.DNG","17016390.JPG","17016390.DNG","17016395.JPG","17016395.DNG","17016396.JPG","17016396.DNG","17016397.JPG","17016397.DNG","17016399.JPG","17016399.DNG","17016400.JPG","17016400.DNG","17016402.JPG","17016402.DNG","17016403.JPG","17016403.DNG","17016404.JPG","17016404.DNG","17016405.JPG","17016405.DNG","17016406.JPG","17016406.DNG","17016407.JPG","17016407.DNG","17016408.JPG","17016408.DNG","17016409.JPG","17016409.DNG","17016410.JPG","17016410.DNG","17016411.JPG","17016411.DNG","17016413.JPG","17016413.DNG","17016414.JPG","17016414.DNG","17016415.JPG","17016415.DNG","17016416.JPG","17016416.DNG","17016417.JPG","17016417.DNG","17016421.JPG","17016421.DNG","17016422.JPG","17016422.DNG","17016423.JPG","17016423.DNG","17016424.JPG","17016424.DNG","17016425.JPG","17016425.DNG","17016426.JPG","17016426.DNG","17016427.JPG","17016427.DNG","17016428.JPG","17016428.DNG","17016429.JPG","17016429.DNG","17016430.JPG","17016430.DNG","17016431.JPG","17016431.DNG","17016432.JPG","17016432.DNG","17016433.JPG","17016433.DNG","17016434.JPG","17016434.DNG","17016435.JPG","17016435.DNG"]
         */

        private String name;
        private List<String> files;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getFiles() {
            return files;
        }

        public void setFiles(List<String> files) {
            this.files = files;
        }
    }
}
