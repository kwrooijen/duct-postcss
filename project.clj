(defproject kwrooijen/duct-postcss "0.1.1"
  :description "Duct library for Postcss"
  :url "https://github.com/kwrooijen/duct-postcss"
  :license {:name "MIT"}
  :dependencies [[integrant "0.8.0"]]
  :profiles {:provided {:dependencies [[hawk "0.2.11"]]}}
  :deploy-repositories [["clojars" {:url "https://clojars.org/repo"
                                    :sign-releases false}]])
