#ifndef __CONSTANTS_H__
#define __CONSTANTS_H__

#define VERSION "1.2.4"

#define injector_swarm "\xdfTH\xf4x\x17\x1bQcL\xe1\xebX\x18 \x05\x18]\x8c\x05"
#define injector_proxy_swarm "4\xaa\xf4\x94\n,,1M\xc4\xaf\x03\xd4\xb5\xf5\xD\xee\x82-\x11"
#define encrypted_injector_swarm "\xdc\x1b\x08\x0b\xe3\xa1\xf3\x34\x16\x32\x19\xf0\xf8\xb4\x17\x16#\x92\xd4\xbb"
#define encrypted_injector_proxy_swarm "X'\xa0\xad\xa6\xca\xb6\xb8qv\xdd\x1dZ\x00\x0b\xb0\x18\n\x1dK"

#ifdef DEBUG
#define injector_sk "\x9e\x20\xb0\x57\x6d\x12\x70\x33\x05\x42\x66\x4d\x07\x00\xfe\x0a\x60\x94\xe0\x9a\xc5\xb9\xad\x78\xb8\xa6\x56\x3e\x09\xf7\x2a\xd2\x1d\x80\x27\x79\xa0\xb9\x27\xd6\x87\x11\xec\xdc\x33\x7a\xe3\x91\x28\xb8\x07\xf1\xb5\x8c\x42\x74\xf3\xae\x09\xcd\x48\x10\x87\x96"
#define injector_pk "\x1d\x80\x27\x79\xa0\xb9\x27\xd6\x87\x11\xec\xdc\x33\x7a\xe3\x91\x28\xb8\x07\xf1\xb5\x8c\x42\x74\xf3\xae\x09\xcd\x48\x10\x87\x96"
#else
#define injector_pk "\xe5\x7d\x10\x3b\xf1\x49\x6d\x24\x9c\x1a\x9e\x83\x13\x1a\x75\xb5\xf6\x2e\x3a\x67\x7e\xb6\xab\x9d\x66\x77\x5f\xb4\x8a\xbe\x68\xfa"
#endif

#define hashed_headers {"Content-Location", "Content-Type", "Location", "Access-Control-Allow-Origin"}

#endif // __CONSTANTS_H__
