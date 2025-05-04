import * as React from "react";
import { cn } from "@/lib/utils";

const Avatar = React.forwardRef<
  HTMLDivElement,
  React.HTMLAttributes<HTMLDivElement> & { src?: string; alt?: string }
>(({ className, src, alt, ...props }, ref) => (
  <div
    ref={ref}
    className={cn(
      "relative flex h-10 w-10 shrink-0 overflow-hidden rounded-full",
      className
    )}
    {...props}
  >
    {src ? (
      <img
        src={src}
        alt={alt}
        className='aspect-square h-full w-full object-cover'
      />
    ) : (
      <div className='flex h-full w-full items-center justify-center rounded-full bg-muted'>
        <span className='text-sm font-medium text-muted-foreground'>
          {alt?.[0]?.toUpperCase()}
        </span>
      </div>
    )}
  </div>
));
Avatar.displayName = "Avatar";

export { Avatar };
