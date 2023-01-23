#!/usr/bin/env deno run -A --unstable
import { runScript } from "https://cdn.jsdelivr.net/npm/bebo@0.0.6/lib/bebo_core.js";
// Add all modules you are going to use within .cljs scripts. They will be bundled into the executable.
import "https://deno.land/std@0.146.0/http/server.ts"
await runScript("server.cljs");
