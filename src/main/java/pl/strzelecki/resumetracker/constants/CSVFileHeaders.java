package pl.strzelecki.resumetracker.constants;

public enum CSVFileHeaders {
    RESUME_TITLE {
        public String toString() {
            return "Title";
        }
    },
    RESUME_EMPLOYER {
        public String toString() {
            return "Employer";
        }
    },
    RESUME_DATE {
        public String toString() {
            return "Date";
        }
    },
    RESUME_RESPONDED {
        public String toString() {
            return "Responded";
        }
    },
    RESUME_URL {
        public String toString() {
            return "Url";
        }
    }
}
