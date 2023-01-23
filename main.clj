#!/usr/bin/env bb
(require '[clojure.string :as str])
(require '[babashka.cli :as cli])
(require '[journal.add :as add])
(require '[journal.list :as list])

(require '[babashka.deps :as deps])
(deps/add-deps '{:deps {medley/medley {:mvn/version "1.3.0"}}})

(require '[babashka.process :refer [sh process check]])

(def cli-opts
  {:entry     {:alias :e
               :desc "Your dream"
               :require true}
   :timestamp {:alias :t
               :desc "A unix timestamp, when you recorded this."
               :coerce {:timestamp :long}}})

(defn help
  [_]
  (-> (sh "whoami") :out print)
  (-> (process "ls") (process "du -h bb.edn") deref :out slurp print)
  (println
   (str "add\n"
        (cli/format-opts {:spec cli-opts}))))

(def table
  [{:cmds ["add"] :fn #(add/add-entry (:opts %)) :spec cli-opts}
   {:cmds ["list"] :fn list/list-entries}
   {:cmds [] :fn help}])

(defn print-person [{:keys [name age]}]
  (println "Name: " name)
  (println "Age: " age))

;; (-> (sh "ls -la") :out str/split-lines first)
; (let [stream (-> (process "ls") :out slurp)]
;   (println (str/split-lines stream)))

;; (print-person {:name "Vampire" :age 35})
;; (cli/dispatch table *command-line-args*)

(let [p (process "sh -c \"for i in `seq 3`; do date; sleep 1; done\"")]
  (println "Waiting for result...")
  ;; dereference to wait for result
  (println (-> p deref :out slurp))
  nil)

