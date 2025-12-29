#!/usr/bin/env bash
#   Use this script to test if a given TCP host/port are available

set -e

cmdname=$(basename "$0")

echoerr() { if [ "$QUIET" -ne 1 ]; then echo "$@" 1>&2; fi }

usage() {
  cat << USAGE >&2
Usage:
  $cmdname host:port [-s] [-t timeout] [-- command args]
  -h HOST | --host=HOST       Host or IP under test
  -p PORT | --port=PORT       TCP port under test
  -s | --strict               Only execute subcommand if the test succeeds
  -q | --quiet                Don't output any status messages
  -t TIMEOUT | --timeout=TIMEOUT
                              Timeout in seconds, zero for no timeout
  -- COMMAND ARGS             Execute command with args after the test finishes
USAGE
  exit 1
}

wait_for() {
  if [ "$TIMEOUT" -gt 0 ]; then
    echoerr "$cmdname: waiting $TIMEOUT seconds for $HOST:$PORT"
  else
    echoerr "$cmdname: waiting for $HOST:$PORT without a timeout"
  fi

  start_ts=$(date +%s)
  while :
  do
    if nc -z "$HOST" "$PORT" >/dev/null 2>&1; then
      end_ts=$(date +%s)
      echoerr "$cmdname: $HOST:$PORT is available after $((end_ts - start_ts)) seconds"
      break
    fi
    sleep 1
  done
  return 0
}

wait_for_wrapper() {
  if ! wait_for; then
    return 1
  fi
  if [ "$STRICT" -eq 1 ]; then
    exec "$@"
  fi
}

HOST=
PORT=
STRICT=0
QUIET=0
TIMEOUT=15

while [ $# -gt 0 ]
do
  case "$1" in
    *:* )
    HOST=$(printf "%s\n" "$1" | cut -d : -f 1)
    PORT=$(printf "%s\n" "$1" | cut -d : -f 2)
    shift 1
    ;;
    -h | --host)
    HOST="$2"
    shift 2
    ;;
    --host=*)
    HOST="${1#*=}"
    shift 1
    ;;
    -p | --port)
    PORT="$2"
    shift 2
    ;;
    --port=*)
    PORT="${1#*=}"
    shift 1
    ;;
    -t | --timeout)
    TIMEOUT="$2"
    shift 2
    ;;
    --timeout=*)
    TIMEOUT="${1#*=}"
    shift 1
    ;;
    -s | --strict)
    STRICT=1
    shift 1
    ;;
    -q | --quiet)
    QUIET=1
    shift 1
    ;;
    --)
    shift
    CMD="$@"
    break
    ;;
    --help)
    usage
    ;;
    *)
    echoerr "Unknown argument: $1"
    usage
    ;;
  esac
done

if [ -z "$HOST" ] || [ -z "$PORT" ]; then
  echoerr "Error: you need to provide a host and port"
  usage
fi

if [ -z "$CMD" ]; then
  wait_for
else
  wait_for_wrapper "$@"
fi
