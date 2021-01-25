# Duct Postcss

[![Clojars Project](https://img.shields.io/clojars/v/duct-postcss.svg)](https://clojars.org/kwrooijen/duct-postcss)

## Usage

`project.clj`

```clojure
:dependencies [[kwrooijen/duct-postcss "0.1.0"]]
```

`config.edn`

```clojure
:duct.module/postcss {:input "myapp/public/css/tailwind.css"
                      :output "myapp/public/style.css"
                      ;; Optional, change the postcss command
                      :postcss ["npx" "postcss"]}
```

## Author / License

Released under the [MIT License] by [Kevin William van Rooijen].

[Kevin William van Rooijen]: https://twitter.com/kwrooijen

[MIT License]: https://github.com/kwrooijen/duct-postcss/blob/master/LICENSE
