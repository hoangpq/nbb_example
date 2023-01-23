(ns server
  (:require
   ["https://deno.land/std@0.146.0/http/server.ts" :as server]))

(def Deno js/Deno)

(def bytes (js/Uint8Array.
            [0,97,115,109,1,0,0,0,1,7,1,96,2,
             127,127,1,127,2,1,0,3,2,1,0,4,1,
             0,5,1,0,6,1,0,7,7,1,3,97,100,100,
             0,0,9,1,0,10,10,1,8,0,32,0,32,1,
             106,15,11,11,1,0]))

(def p
  (js/Promise.
   (fn [resolve _]
     (let [exports (. js/WebAssembly (instantiate bytes))]
       (-> exports
           (.then (fn [exports]
                    (let [m (-> exports .-instance .-exports)]
                      (resolve m)))))))))

(def port 8080)

(defn handler [m req]
  (let [agent (-> req (.-headers) (.get "user-agent"))
        body (str "Your user agent is: "
                  (or agent
                      "Unknown") "\n" "Wasm execution: 1 + 200 = " (.add m 1 200))]
    (new js/Response body #js {:status 200})))

(.then p (fn [m]
           (server/serve #(handler m %) #js {:port port})))
