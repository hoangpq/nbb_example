(ns script
  (:require ["csv-parse/sync" :as csv]
            ["fs" :as fs]
            ["path" :as path]
            ["shelljs$default" :as sh]
            ["term-size$default" :as term-size]
            ["zx" :refer [$]]
            ["zx$fs" :as zxfs]
            [nbb.core :refer [*file*]]))

(prn (path/resolve "."))

(prn (term-size))

(println (count (str (fs/readFileSync *file*))))

(prn (sh/ls "."))

(prn (csv/parse "foo,bar"))

(prn (zxfs/existsSync *file*))

;; function to add 2 numbers
(defn add [a b] (+ a b))

($ #js ["ls"])
(println (str "Result: " (add 1 2)))

