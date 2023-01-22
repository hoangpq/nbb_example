#!/usr/bin/env bb
(require '[babashka.cli :as cli])
(require '[journal.add :as add])

(def cli-opts
  {:entry     {:alias :e
               :desc "Your dream"
               :require true}
   :timestamp {:alias :t
               :desc "A unix timestamp, when you recorded this."
               :corce {:timestamp :long}}})

(defn help
  [_]
  (println
   (str "add\n"
        (cli/format-opts {:spec cli-opts}))))

(def table
  [{:cmds ["add"] :fn add/add-entry :opts cli-opts}
   {:cmds [] :fn help}])

(defn print-person [{:keys [name age]}]
  (println "Name: " name)
  (println "Age: " age))

;; (print-person {:name "Vampire" :age 35})
;; (cli/dispatch table *command-line-args*)



